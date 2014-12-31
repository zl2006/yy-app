/*
* 文 件 名:  AbstractTicket.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  令牌抽像数据
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso.ticket;

/**
* 令牌抽像数据
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public abstract class AbstractTicket implements Ticket, TicketState {
    
    private static final long serialVersionUID = 5100850362552503943L;
    
    /** 令牌唯一标识 */
    private String id;
    
    /** 令牌最后使用时间 */
    private long lastTimeUsed;
    
    /** 令牌上一次使用时间 */
    private long previousLastTimeUsed;
    
    /**令牌创建时间*/
    private long creationTime;
    
    /**令牌使用次数*/
    private int countOfUses;
    
    /**获取身份标识*/
    private String credentialId;
    
    /**
     * 构建令牌
     *
     * @param id 令牌标识
     * @throws TicketException 当ID为空时报参数异常
     */
    public AbstractTicket(final String id, final String credentialId)
        throws TicketException {
        if (id == null) {
            throw new TicketException("参数异常，令牌标识为空");
        }
        if (credentialId == null) {
            throw new TicketException("参数异常，身份标识为空");
        }
        this.id = id;
        this.credentialId = credentialId;
        this.creationTime = System.currentTimeMillis();
        this.lastTimeUsed = System.currentTimeMillis();
    }
    
    /** {@inheritDoc} */
    @Override
    public long getCreationTime() {
        return this.creationTime;
    }
    
    /** {@inheritDoc} */
    @Override
    public int getCountOfUses() {
        return this.countOfUses;
    }
    
    /** {@inheritDoc} */
    @Override
    public long getLastTimeUsed() {
        return this.lastTimeUsed;
    }
    
    /** {@inheritDoc} */
    @Override
    public long getPreviousTimeUsed() {
        return this.previousLastTimeUsed;
    }
    
    /** {@inheritDoc} */
    @Override
    public String getId() {
        return this.id;
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean isExpired(ExpirationPolicy expirationPolicy) {
        return expirationPolicy.isExpired(this);
    }
    
    public final void updateState() {
        this.previousLastTimeUsed = this.lastTimeUsed;
        this.lastTimeUsed = System.currentTimeMillis();
        this.countOfUses++;
    }
    
    /** {@inheritDoc} */
    @Override
    public String getCredentialId() {
        return this.credentialId;
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractTicket other = (AbstractTicket)obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "AbstractTicket [id=" + id + ", lastTimeUsed=" + lastTimeUsed + ", previousLastTimeUsed="
            + previousLastTimeUsed + ", creationTime=" + creationTime + ", countOfUses=" + countOfUses
            + ", credentialId=" + credentialId + "]";
    }
    
}
